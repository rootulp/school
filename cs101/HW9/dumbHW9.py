def take(row, col):
    global cells
    for tile in tileList:
        cells[row][col].image = tile
        image = PhotoImage(file="Cell Images/"+tile)
        cells[row][col].config = image 
        window.update_idletasks()
        if checkCell(row, col) == True:
            if checkEndpoint() == True:
                if solve() == True:
		    return True
        elif tileCount < len(tileList)-1:
	    tileCount += 1
	    take(row, col)
        else:
	    curr.config(image = empty)
	    curr.image = empty
	    tileCount = 0
	    return False
            
