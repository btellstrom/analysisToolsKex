filename = "generatedConfig.cfg"

file = open(filename, "w")

file.write("nrOfMeasurements=1000\n")

#want length to be 50 equidistant steps between 200 and 10.000
stringToBeAppended = "listLength="

for i in range(1,51):
    stringToBeAppended += str(i*200) + ","

file.write(stringToBeAppended)
file.write("\n")

#want nrOfSorts to be 50 equidistant steps between 100 and 5000

stringToBeAppended = "nrOfSorts="

for i in range(1,51):
    stringToBeAppended += str(i*50) + ","

file.write(stringToBeAppended)
file.write("\n")

#the rest
file.write("nrOfLists=1\n")
file.write("sortingAlgorithms=QuickSort,BubbleSort,SelectionSort\n")
file.write("fileFamily=initialTestOfVariance/initalTestOfVarianceWithLoopsOverLengthAndNrOfSorts\n")
