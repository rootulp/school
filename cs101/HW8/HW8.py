#Rootul Patel
#HW8 - PhotoShop

from Tkinter import *
import BCImage

def read():                                                 #Reads in an image file and displays it
    global photo
    photo = PhotoImage(file=photoEntry.get())
    canvas = Canvas(window,width = photo.width(), height = photo.height())
    canvas.grid(row = 0, column = 0, columnspan = 5)
    canvas.create_image(0,0,image = photo, anchor = NW)
    
def gray():                                                  #Make image gray
    global photo
    pixels = BCImage.getPixels(photo)
    for row in range(photo.height()):
        for col in range(photo.width()): 
            r = pixels[row][col][0]                   #Grab pixels
            g = pixels[row][col][1]
            b = pixels[row][col][2]
            gray = r*.3 + g*.59 + b*.11         #Formula to gray pixels
            pixels[row][col]=[gray,gray,gray]
    BCImage.setPixels(photo,pixels)

def blur():
    global photo
    pixels = BCImage.getPixels(photo)
    for row in range(1,photo.height()-1):
        for col in range(1,photo.width()-1):
            r = pixels[row][col][0]
            g = pixels[row][col][1]
            b = pixels[row][col][2]
            newR = (pixels[row-1][col-1][0] + pixels[row-1][col][0] + pixels[row-1][col+1][0]+ pixels[row][col-1][0] + pixels[row][col][0] + pixels[row][col+1][0] + pixels[row+1][col-1][0] + pixels[row+1][col][0] + pixels[row+1][col+1][0])/9.0
            newG = (pixels[row-1][col-1][1] + pixels[row-1][col][1] + pixels[row-1][col+1][1]+ pixels[row][col-1][1] + pixels[row][col][1] + pixels[row][col+1][1] + pixels[row+1][col-1][1] + pixels[row+1][col][1] + pixels[row+1][col+1][1])/9.0
            newB = (pixels[row-1][col-1][2] + pixels[row-1][col][2] + pixels[row-1][col+1][2]+ pixels[row][col-1][2] + pixels[row][col][2] + pixels[row][col+1][2] + pixels[row+1][col-1][2] + pixels[row+1][col][2] + pixels[row+1][col+1][2])/9.0 
            pixels[row][col]=[newR, newG, newB]
    BCImage.setPixels(photo,pixels)

def lighten():
    global photo
    pixels = BCImage.getPixels(photo)
    for row in range(photo.height()):
        for col in range(photo.width()):
            r = pixels[row][col][0]
            g = pixels[row][col][1]
            b = pixels[row][col][2]
            newR = ((r/255.0)**.8)*255
            newG = ((g/255.0)**.8)*255
            newB = ((b/255.0)**.8)*255
            pixels[row][col]=[newR,newG,newB]
    BCImage.setPixels(photo,pixels)

def darken():
    global photo
    pixels = BCImage.getPixels(photo)
    for row in range(photo.height()):
        for col in range(photo.width()):
            r = pixels[row][col][0]
            g = pixels[row][col][1]
            b = pixels[row][col][2]
            newR = ((r/255.0)**(1/.8))*255
            newG = ((g/255.0)**(1/.8))*255
            newB = ((b/255.0)**(1/.8))*255
            pixels[row][col]=[newR,newG,newB]
    BCImage.setPixels(photo,pixels)

def pixelate():
    global photo
    pixels = BCImage.getPixels(photo)
    for row in range(photo.height()):
        for col in range(photo.width()):
            if row == 0 and col == 0:
                newR = pixels[row][col][0]
                newG = pixels[row][col][1]
                newB = pixels[row][col][2]
                for r in range(10):
                    for c in range(10):
                        pixels[row+r][col+c] = [newR,newG,newB]
            elif row % 10 == 0 and col % 10 == 0:
                newR = pixels[row][col][0]
                newG = pixels[row][col][1]
                newB = pixels[row][col][2]
                for r in range(10):
                    for c in range(10):
                        if row+r > photo.height()-1:
                            r = photo.height()-1-row
                        elif col+c > photo.width()-1:
                            c = photo.height()-1-col
                        else:
                            pixels[row+r][col+c] = [newR,newG,newB]
    BCImage.setPixels(photo,pixels)

def sharpen():
    global photo
    pixels = BCImage.getPixels(photo)
    blur()
    blurPixels = BCImage.getPixels(photo)
    for row in range(1,photo.height()-1):
        for col in range(1,photo.width()-1):
            blurR = blurPixels[row][col][0]
            blurG = blurPixels[row][col][1]
            blurB = blurPixels[row][col][2]
            regR = pixels[row][col][0]
            regG = pixels[row][col][1]
            regB = pixels[row][col][2]
            newR = regR + (regR - blurR)
            newG = regG + (regG - blurG) 
            newB = regB + (regB - blurB)
            if newR > 255:
                newR = 255
            elif newR < 0:
                newR = 0
            if newG > 255:
                newG = 255
            elif newG < 0:
                newG = 0
            if newB > 255:
                newB = 255
            elif newB < 0:
                newB = 0
            pixels[row][col]=[newR, newG, newB]
    BCImage.setPixels(photo,pixels)

def saturate():
    global photo
    pixels = BCImage.getPixels(photo)
    for row in range(photo.height()):
        for col in range(photo.width()):
            r = pixels[row][col][0]
            g = pixels[row][col][1]
            b = pixels[row][col][2]
            if r >= 128:
                newR = 255
            else:
                newR = 0
            if g >= 128:
                newG = 255
            else:
                newG = 0
            if b >= 128:
                newB = 255
            else:
                newB = 0
            pixels[row][col]=[newR,newG,newB]
    BCImage.setPixels(photo,pixels)

def colorblind():
    global photo
    pixels = BCImage.getPixels(photo)
    for row in range(photo.height()):
        for col in range(photo.width()):
            r = pixels[row][col][0]
            g = pixels[row][col][1]
            b = pixels[row][col][2]
            newB = (g+b)/2
            newG = (g+b)/2
            pixels[row][col]=[r,newG,newB]
    BCImage.setPixels(photo,pixels)

def fliph():
    global photo
    pixels = BCImage.getPixels(photo)
    oldpixels = BCImage.getPixels(photo)
    for row in range(photo.height()):
        for col in range(photo.width()-1,-1,-1):
            r = oldpixels[row][col][0]
            g = oldpixels[row][col][1]
            b = oldpixels[row][col][2]
            pixels[row][photo.width()-col-1] = [r,g,b]
    BCImage.setPixels(photo,pixels)
    
def flipv():
    global photo
    pixels = BCImage.getPixels(photo)
    newv = []
    for row in range(photo.height()-1,-1,-1):
        newv.append(pixels[row])
    BCImage.setPixels(photo,newv)

def edges():
    global photo
    pixels = BCImage.getPixels(photo)
    for row in range(0,photo.height()-1):
        for col in range(0,photo.width()-1):
                r = pixels[row][col][0]
                g = pixels[row][col][1]
                b = pixels[row][col][2]
                gray = r*.3 + g*.59 + b*.11
                r1 = pixels[row][col+1][0]
                g1 = pixels[row][col+1][1]
                b1 = pixels[row][col+1][2]
                gray1 = r1*.3 + g*.59 + b*.11
                if gray - gray1 >= 20 or gray1 - gray >= 20:
                    pixels[row][col] = [255,255,255]
                else:
                    pixels[row][col] = [0,0,0]
    BCImage.setPixels(photo,pixels)
    
window = Tk()
window.title("BC Photoshop")

photoEntry = Entry(window)
photoEntry.grid(row = 1, column = 0, columnspan = 3)

readButton = Button(window, text = "Read", command = read)
readButton.grid(row = 1, column = 3, columnspan = 2, sticky = 'NSEW')

grayButton = Button(window, text = "Gray", command = gray)
grayButton.grid(row = 2, column = 0, sticky = 'NSEW')

blurButton = Button(window, text = "Blur", command = blur)
blurButton.grid(row = 2, column = 1, sticky = 'NSEW')

lightenButton = Button(window, text = "Lighten", command = lighten)
lightenButton.grid(row = 2, column = 2, sticky = 'NSEW')

darkenButton = Button(window, text = "Darken", command = darken)
darkenButton.grid(row = 2, column = 3, sticky = 'NSEW')

pixelateButton = Button(window, text = "Pixelate", command = pixelate)
pixelateButton.grid(row = 2, column = 4, sticky = 'NSEW')

sharpenButton = Button(window, text = "Sharpen", command = sharpen)
sharpenButton.grid(row = 3, column = 0, sticky = 'NSEW')

saturateButton = Button(window, text = "Saturate", command = saturate)
saturateButton.grid(row = 3, column = 1, sticky = 'NSEW')

colorblindButton = Button(window, text = "Color Blind", command = colorblind)
colorblindButton.grid(row = 3, column = 2, sticky = 'NSEW')

fliphButton = Button(window, text = "FlipH", command = fliph)
fliphButton.grid(row = 3, column = 3, sticky = 'NSEW')

flipvButton = Button(window, text = "FlipV", command = flipv)
flipvButton.grid(row = 3, column = 4, sticky = 'NSEW')

edgesButton = Button(window, text = "Edges", command = edges)
edgesButton.grid(row = 4, column = 0, sticky = 'NSEW')

window.mainloop()
