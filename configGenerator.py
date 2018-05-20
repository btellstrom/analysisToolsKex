filename = "generatedConfig.cfg"

file = open(filename, "w")

file.write("nrOfMeasurements=1000\n")

#want length to be 100 equidistant steps between 500 and 50.000
stringToBeAppended = "listLength="

for i in range(1,100):
    stringToBeAppended += str(i*500) + ","

file.write(stringToBeAppended)
file.write("\n")

#want nrOfSorts to be 50 equidistant steps between 100 and 5000

stringToBeAppended = "nrOfSorts="

for i in range(1,50):
    stringToBeAppended += str(i*50) + ","

file.write(stringToBeAppended)
file.write("\n")

#the rest
file.write("nrOfLists=1\n")
file.write("sortingAlgorithms=QuickSort,BubbleSort,SelectionSort\n")
file.write("fileFamily=initalTestOfVarianceWithLoopsOverLengthAndNrOfSorts\n")
