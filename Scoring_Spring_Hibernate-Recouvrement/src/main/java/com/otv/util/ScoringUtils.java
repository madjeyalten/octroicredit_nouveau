package com.otv.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.trees.J48;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class ScoringUtils {

	 public static BufferedReader readDataFile(String filename) {
			BufferedReader inputReader = null;
	 
			try {
				inputReader = new BufferedReader(new FileReader(filename));
			} catch (FileNotFoundException ex) {
				System.err.println("File not found: " + filename);
			}
	 
			return inputReader;
		}
	 
		public static Evaluation classify(Classifier model,
				Instances trainingSet, Instances testingSet) throws Exception {
			Evaluation evaluation = new Evaluation(trainingSet);
	 
			model.buildClassifier(trainingSet);
			evaluation.evaluateModel(model, testingSet);
	 
			return evaluation;
		}
	 
		public static double calculateAccuracy(FastVector predictions) {
			double correct = 0;
	 
			for (int i = 0; i < predictions.size(); i++) {
				NominalPrediction np = (NominalPrediction) predictions.elementAt(i);
				if (np.predicted() == np.actual()) {
					correct++;
				}
			}
	 
			return 100 * correct / predictions.size();
		}
	 
		public static Instances[][] crossValidationSplit(Instances data, int numberOfFolds) {
			Instances[][] split = new Instances[2][numberOfFolds];
	 
			for (int i = 0; i < numberOfFolds; i++) {
				split[0][i] = data.trainCV(numberOfFolds, i);
				split[1][i] = data.testCV(numberOfFolds, i);
			}
	 
			return split;
		}
	 
		public static void main(String[] args) throws Exception {
			
			
			//load model
			 J48 tree = new J48();         // new instance of tree    // set the options
	      //predict instance class values
	        
	        BufferedReader reader = new BufferedReader(
	                new FileReader("weather.txt"));
	        BufferedReader readerBis = new BufferedReader(
	                new FileReader("weather.txt"));
	           Instances testInstance = new Instances(reader);
	           testInstance.setClassIndex(testInstance.numAttributes() - 1);
	         //load training instances

	           //build a J48 decision tree
	           J48 model=new J48(); 
	           model.buildClassifier(testInstance);

	           //decide which instance you want to predict
	           int s1=2;
	           Instance currentInstance=new Instance(3);
	           currentInstance.setDataset(testInstance);
	           currentInstance.setValue(0, 40);
	           currentInstance.setValue(1, 400);

	           //get the predicted probabilities 
	           double[] prediction=model.distributionForInstance(currentInstance);

	           //output predictions
	           for(int i=0; i<prediction.length; i=i+1)
	           {
	               System.out.println("Probability of class "+
	            		   testInstance.classAttribute().value(i)+
	                                  " : "+Double.toString(prediction[i]));
	           }
			}
	}

