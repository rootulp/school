# Module for sound manipulation
# William Ames, Fall 2013

# bugs: need to catch ctrl-c, abort play and close output stream
# switch to tkSnack
# play in background, stop function to interrupt
# a Tkinter based progress bar for reading (and playing) would be nice, it takes a while

import pyaudio
import wave
import sys
import signal
import threading

wf = None
left  = []
right = []
outputStream = None
bigEndian = False
chunk = 1024 * 10 # block size to process; must be multiple of 4, preferably large
p = pyaudio.PyAudio()
interrupt = False

def read(filename):
    """Reads a wave file, by name.  Reads the data into internal lists
which can be obtained by the getLeft() and getRight() functions."""
    global wf, left, right, outputStream, bigEndian, chunk
    if wf != None:
        wf.close()
        wf = None
    wf = wave.open(filename, "rb")
    left  = []
    right = []

    # read all of the data into the lists
    data = wf.readframes(chunk)
    while data != '':
        for i in range(0,len(data),2):
            if bigEndian:
                byte1 = ord(data[i])
                byte2 = ord(data[i+1])
            else:
                byte2 = ord(data[i])
                byte1 = ord(data[i+1])
            sample = (byte1<<8) | byte2
            if sample >= (1<<15):
                sample = sample-65536
            if i/2%2==0:
                left.append(sample/32768.) # float, -1 to 1
            else:
                right.append(sample/32768.)
        data = wf.readframes(chunk)
    #wf.close()

def getLeft():
    """Returns a list containing the samples for the left channel.
    Each sample is a float in the range of -1 to 1 inclusive."""
    global wf, left, right, outputStream, bigEndian, chunk
    if wf==None:
        print "Error, no audio file has been read."
        return None
    return left

def getRight():
    """Returns a list containing the samples for the left channel.
    Each sample is a float in the range of -1. to 1. inclusive."""
    global wf, left, right, outputStream, bigEndian, chunk
    if wf==None:
        print "Error, no audio file has been read."
        return None
    return right

# for internal use only!
def _realPlay():
    global wf, left, right, outputStream, bigEndian, chunk, interrupt
    interrupt = False
    if wf==None:
        print "Error, no audio file has been read."
        return None
    if outputStream != None:
        outputStream.close()
    if len(left) != len(right):
        print "Error, left and right are not the same length."
        print "Left channel is length", len(left), "but right channel is length", len(right)
        return
    # open output stream
    outputstream = p.open(format = p.get_format_from_width(wf.getsampwidth()),
                channels = wf.getnchannels(),
                rate = wf.getframerate(),
                output = True)
    result=""
    for i in range(len(left)):
        if left[i] > 1 or left[i] < -1:
            print "Error, left[" + str(i) + "] out of range:", left[i]
            outputstream.close()
            return
        if right[i] > 1 or right[i] < -1:
            print "Error, right[" + str(i) + "] out of range:", right[i]
            outputstream.close()
            return
        sample = int(left[i]*32767)
        newByte1 = (sample >> 8) & 255
        newByte2 = sample & 255
        if bigEndian:
            result += chr(newByte1)
            result += chr(newByte2)
        else:
            result += chr(newByte2)
            result += chr(newByte1)
        
        sample = int(right[i]*32767)
        newByte1 = (sample >> 8) & 255
        newByte2 = sample & 255
        if bigEndian:
            result += chr(newByte1)
            result += chr(newByte2)
        else:
            result += chr(newByte2)
            result += chr(newByte1)
        if (len(result) >= chunk):
            outputstream.write(result)
            result = ""
        if (interrupt):
            return #aborted early
    #finished for, check for residual stuff still left in result
    if result != "":
        outputstream.write(result)
    result = ""
    outputstream.close()

def play():
    """Plays the sample in the left, right lists.
The lists must be the same length, and each value must be
in the range of -1. to 1."""
    threading.Thread(target=_realPlay).start()

def stop():
    """If a sound is currently playing, stop it."""
    global interrupt
    interrupt = True
