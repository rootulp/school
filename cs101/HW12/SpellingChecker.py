#Rootul Patel
#HW12 - Spelling Checker

from Tkinter import *
import tkFileDialog

class SpellingChecker():

    def __init__(self, text1, text2):
        self.text1 = text1
        self.text2 = text2
        fin = open("dictionary.txt", 'r') 
        content = fin.read()
        fin.close()
        dictWords = re.findall("[a-zA-Z]+", content)
        dictWords.sort()
        self.dictWords = dictWords

    def loadFile(self):
        try:
            fileToReadFrom = tkFileDialog.askopenfilename()
            print "Reading from file", fileToReadFrom
            fin = open(fileToReadFrom, 'r') 
            content = fin.read()
            fin.close()
            self.text1.insert(0.0, content)
        except:
            print "Unable to read the file"
            return

    def saveFile(self):
        try:
            content = self.text1.get(0.0, END)
            fileToWriteTo = tkFileDialog.asksaveasfilename()
            print "Writing to file", fileToWriteTo
            fout = open(fileToWriteTo, 'w') 
            fout.write(content)
            fout.close() 
        except:
            print "Unable to write the file, sorry."
            return

    def spellCheck(self):
        inputWords = self.text1.get(0.0, END)
        inputWordsList = re.findall("[a-zA-Z]+", inputWords)
        misspelledWordsList = []
        for word in inputWordsList:
            if SpellingChecker.isWordInDictionary(self, word) == False:
                misspelledWordsList.append(str(word))
        string = ""
        #The HW Sheet didn't clarify how to format our output 
        for word in misspelledWordsList:
            string = string + word + " "
        self.text2.insert(0.0, string)
        #Instead of the above, could just ouput the whole list:
        #self.text2.insert(0.0, misspelledWordsList)

    def isWordInDictionary(self, word):
        word = word.lower()
        found = False
        first = 0
        last = len(self.dictWords) - 1
        while first <= last and not found:
            mid = (first+last)/2
            if self.dictWords[mid] == word:
                found = True
            elif self.dictWords[mid] < word:
                first = mid+1
            else:
                last = mid-1
        return found

    # def isWordInDictionary(self, word):
    #     for realWord in self.dictWords:
    #         if word.lower() == realWord:
    #             return True
    #     return False