# BCImage
# Get 2D list of pixels, indexed by [row][col].
# Each element is a list [red, green, blue]
# (So, it's really 3D ...)

# Return the red, green, blue values of the pixel at location (x,y) of the photo.
def _get_pixel(photo, x, y):
    p = photo.get(x, y)
    vals = p.split(" ")
    r = int(vals[0])
    g = int(vals[1])
    b = int(vals[2])
    return [r,g,b]

# Replace pixel at pos=(x,y) on photo, with colors r,g,b.
def _put_pixel(photo, x,y, r,g,b):
    photo.put("#%02x%02x%02x" % (r,g,b), (x,y))


def getPixels(photo):
    """Get the pixels from the photo into a 3D list"""
    pixels = []
    for row in range(photo.height()):
        pixels.append( [] )
        for col in range(photo.width()):
            pixels[row].append(_get_pixel(photo, col, row))
    return pixels

def setPixels(photo, pixels):
    """Set each pixel of the photo from the colors in the pixels 3D list"""
    for row in range(len(pixels)):
        for col in range(len(pixels[row])):
            _put_pixel(photo, col,row, pixels[row][col][0], pixels[row][col][1], pixels[row][col][2])

            
