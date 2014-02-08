#Rootul Patel 9/25/13
#HW5 - Encryption

from Tkinter import *

window = Tk()
index = []

def encrypt():
    text = textEntry.get()
    password = passwordEntry.get()
    index = range(len(text))
    result = ""
    textEntry.delete(0, END)
    for i in index:
        textChar = text[i]
        passwordChar = password[len(index) % len(password)]
        textChar = ord(textChar)
        passwordChar = ord(passwordChar)
        numResult = ((textChar - 32 + passwordChar) % (127-32)) + 32
        charResult = chr(numResult)
        result = result + charResult
    textEntry.insert(0, result)
        
def decrypt():
    text = textEntry.get()
    password = passwordEntry.get()
    index = range(len(text))
    result = ""
    textEntry.delete(0, END)
    for i in index:
        textChar = text[i]
        passwordChar = password[len(index) % len(password)]
        textChar = ord(textChar)
        passwordChar = ord(passwordChar)
        numResult = ((textChar - 32 - passwordChar) % (127-32)) + 32
        charResult = chr(numResult)
        result = result + charResult
    textEntry.insert(0, result)
    
def clear():
    textEntry.delete(0, END)

encryptButton = Button(window, text = "Encrypt", command = encrypt)
encryptButton.grid(row=0, column = 0, sticky = 'NSEW')

decryptButton = Button(window, text = "Decrypt", command = decrypt)
decryptButton.grid(row=0, column = 1, sticky = 'NSEW')

clearButton = Button(window, text = "Clear", command = clear)
clearButton.grid(row=0, column = 2, sticky = 'NSEW')

passwordLabel = Label(window, text = "Password:")
passwordLabel.grid(row = 0, column = 3)

passwordEntry = Entry(window)
passwordEntry.grid(row = 0, column = 4, columnspan = 3, sticky = 'NSEW')

textEntry = Entry(window)
textEntry.grid(row = 1, column = 0, columnspan = 7, sticky = 'NSEW')

window.mainloop()
