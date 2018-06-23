package piejohnnylikes;

import java.util.List;

import algorithm.hillclimbsearch.EvaluationFunction;

public class PieEvaluationFunction implements EvaluationFunction<PieClassifier, Double, Boolean, Pie> {

	@Override
	public Double evaluate(PieClassifier currentState, List<Pie> trainingSet) {
		int successfullyClassifiedCount = 0;
		for (Pie pie : trainingSet) {
			boolean value = currentState.getDescriptor().eval(pie);
			if ((pie.getInputClass() && value) || (!pie.getInputClass() && !value)) {
				successfullyClassifiedCount++;
			}
		}
		return 1.00 - (double) successfullyClassifiedCount / (double) trainingSet.size();
	}

}
