import matplotlib.pyplot as plt
import pandas
df=pandas.read_csv('111(05 52 14 PM).csv',sep=':')
plt.plot(df['time'],df['val'])
plt.show()
