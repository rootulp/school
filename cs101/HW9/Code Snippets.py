# Code Snippets for Flow Solver
# Below are a few code snippets, intended to save you the time and tedium
# of typing in file name lists.  Copy and paste these into your program as needed.

# Snippet number 1: List of colors used.  Not sure if you need this or not.
# By the way, the "white" files are really gray.
colorList = ["blue", "green", "red", "yellow", "cyan", "orange", "magenta", "pink", "white"];



# Snippet number 2: a list of the EndPoint tile file names
endPointFileList = ["blueEndpoint.gif",
                    "greenEndpoint.gif",
                    "redEndpoint.gif",
                    "yellowEndpoint.gif",
                    "cyanEndpoint.gif",
                    "orangeEndpoint.gif",
                    "magentaEndpoint.gif",
                    "pinkEndpoint.gif",
                    "whiteEndpoint.gif"   ]



# Snippet number 3: a list of the playable tile files names
fileList = ["blueHorizontal.gif",    "blueQ1.gif",    "blueQ2.gif",    "blueQ3.gif",    "blueQ4.gif",    "blueVertical.gif",
            "greenHorizontal.gif",   "greenQ1.gif",   "greenQ2.gif",   "greenQ3.gif",   "greenQ4.gif",   "greenVertical.gif",
            "redHorizontal.gif",     "redQ1.gif",     "redQ2.gif",     "redQ3.gif",     "redQ4.gif",     "redVertical.gif",
            "yellowHorizontal.gif",  "yellowQ1.gif",  "yellowQ2.gif",  "yellowQ3.gif",  "yellowQ4.gif",  "yellowVertical.gif",
            "cyanHorizontal.gif",    "cyanQ1.gif",    "cyanQ2.gif",    "cyanQ3.gif",    "cyanQ4.gif",    "cyanVertical.gif",
            "orangeHorizontal.gif",  "orangeQ1.gif",  "orangeQ2.gif",  "orangeQ3.gif",  "orangeQ4.gif",  "orangeVertical.gif",
            "magentaHorizontal.gif", "magentaQ1.gif", "magentaQ2.gif", "magentaQ3.gif", "magentaQ4.gif", "magentaVertical.gif",
            "pinkHorizontal.gif",    "pinkQ1.gif",    "pinkQ2.gif",    "pinkQ3.gif",    "pinkQ4.gif",    "pinkVertical.gif",
            "whiteHorizontal.gif",   "whiteQ1.gif",   "whiteQ2.gif",   "whiteQ3.gif",   "whiteQ4.gif",   "whiteVertical.gif"    ]



# Snippet number 4: the isAllowedRight() function that we developed in class
# curr and right are PhotoImages.  All photoimage should have been annotated with goesRight, color, etc when created.
# this one I'm giving to the students
def isAllowedRight(curr, right):
    if right==empty:
        return True
    if curr.goesRight and right.goesLeft and curr.color==right.color:
        return True
    if not curr.goesRight and not right.goesLeft:
        return True
    if not curr.goesRight and right in endPoints:
        return True
    return False


My Notes

empty = PhotoImage(file = "Empty.gif")
empty.goesLeft = False
empty.goesRight = False

#Green Horizontal
greenHoriz = PhotoImage(file = "greenHorizontal.gif")
greenHoriz.goesLeft = True
greenHoriz.goesRight = True
greenHoriz.goesUp = False
greenHoriz.color = "green"

def isAllowedRight(curr, right):
    if right == emtpy:
        return True
    if curr.goesRight and right.goesLeft and curr.color == right.color:
        return True
    if not curr.right and not right.goesLeft:
        return True
    if not curr.goesRight and right in endPoints:
        return True
    return False

#def take(row, cell)
#   for examine all playable images
#   put images into cells[row][col]
#   check allowed left/right/up/down
#        and solve()
#   return True
#   otherwise continue loop
#   if none work, return false cause no images work in that cell

#def solve():
#    loop through all rows and cols
#        if cell[row][col] is empty:
#            if take (row, col)
#                return true
#            else
#                return false
#        continue row/cel loop
#    if loop finishes return true
    
                

