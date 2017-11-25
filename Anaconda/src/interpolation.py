import numpy as np
from scipy import interpolate
import matplotlib.pyplot as plt 
x=np.linspace(0,10,11)
y=np.sin(x)
f = interpolate.interp1d(x,y,"quadratic")
xnew=np.linspace(0,10,101)
ynew =f(xnew)
plt.plot(x,y,xnew,ynew,'r-*')
plt.show()