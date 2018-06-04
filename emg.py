import pandas
import numpy as np
import matplotlib.pyplot as plt
from scipy.ndimage import gaussian_filter1d
from scipy.signal import argrelextrema

def function (loc):
	df=pandas.read_csv(loc,sep=':')
	default_val=df.val[1]
	default_time=df.time[1]
	df['val']=abs(df.val-default_val)
	y = gaussian_filter1d(df['val'], 4)
	#plt.plot(df['time'],df['val'])
	x=np.array(df['time'])
	max_y = max(y)  # Find the maximum y value
	max_x = df.time[y.argmax()]  # Find the x value corresponding to the maximum y value
	t=600000
	l=100000
	r=100000
	ans='no ans'
	for i in range(len(x)-1):
		if(x[i] in range(max_x-4000,max_x+4000)):
			if(y[i]>50):
				while(y[i]<y[i+1] and y[i]!=y[i+1] ):	
					t=y[i]
					r=x[i]
					l=i
					i=i+1
				break

	threshold=t*1.1
	for i in range(l+1,len(x)-1):
		if(y[i]>=threshold):
			print(y[i],x[i],x[i]-r)
			ans=str(x[i]-r)
			break
	f=open("results.txt",'a')
	f.write(ans+'\n')
