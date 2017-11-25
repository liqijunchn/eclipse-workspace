import numpy as np
from scipy import interpolate
from sympy import symbols,expand,init_printing

x0=0
x1=1
x2=3
y0=1
y1=2
y2=3
x=symbols('x')
L=(x-x1)*(x-x2)/((x0-x1)*(x0-x2))*y0+(x-x0)*(x-x2)/((x1-x0)*(x1-x2))*y1+(x-x0)*(x-x1)/((x2-x0)*(x2-x1))*y2
init_print(L)