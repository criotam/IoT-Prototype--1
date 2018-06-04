import os
from emg import function
'''
for filename in os.listdir('/home/sam/Desktop/03-06-2018'):
	if filename.endswith(".csv") :
		with open(filename,'r') as f:
			with open('newfile.txt','w') as f2: 
				f2.write('id:val:time\n')
				f2.write(f.read())
		os.rename('newfile.txt',filename)
'''	
directory='/home/sam/Desktop/03-06-2018'
for filename in os.listdir('/home/sam/Desktop/03-06-2018'):
	if filename.endswith(".csv") :
		print(os.path.join(directory, filename))
		function(os.path.join(directory, filename))
		continue
	else:
		continue

