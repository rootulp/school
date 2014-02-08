#Rootul Patel
#HW12 - Spelling Checker

from Tkinter import *
from SpellingChecker import *

# Build GUI
window = Tk()
window.title("Spelling Checker")
label1 = Label(window, text = "Words to check:")
label1.grid(row = 0, column = 0)
text1 = Text(window, height = 15)
text1.grid(row = 1, column = 0, columnspan = 3)
label2 = Label(window, text = "Misspelled words:")
label2.grid(row = 2, column = 0)
text2 = Text(window, height = 15)
text2.grid(row = 3, column = 0, columnspan = 3)
button1 = Button(window, text = "Check Spelling")
button1.grid(row = 4, column = 0)
button2 = Button(window, text = "Load File")
button2.grid(row = 4, column = 1)
button3 = Button(window, text = "Save File")
button3.grid(row = 4, column = 2)
ABC = SpellingChecker(text1, text2)
button1.config(command = ABC.spellCheck)
button2.config(command = ABC.loadFile)
button3.config(command = ABC.saveFile)

window.mainloop()

#lst = re.findall("[a-zA-Z]+",str)
#from text widget
#text.delete("0.0", END)
#First 0  refers to row, second 0 refers to column

#Do once in __init__
#Open dictionary.txt
#read from file into filecontent
#dictWords = re.findall("", fileContent)

#Linear Search: 
# For w in list:
#     if linearSearch(w, dictWords) == False:
#         Add word to misspelled words

# def linearSearch(w, dictWords):
#     for d in dictWords:
#         if w == d:
#             return True
#     return False

# Binary Search
# def binarySearch(self, word, dict, first, last):
#     calculate middle, average of first, last
#     does word match list element in middle?
#     if word comes alphabetically after middle word:
#         search list again, middle + 1 through last <- recursive calculate
#     else word comes alphabeticall before middle word:
#         search list again, first through middle - 1 <- recursive calculate

