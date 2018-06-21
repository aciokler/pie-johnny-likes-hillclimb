package piejohnnylikes;

import java.util.List;

import algorithm.hillclimbsearch.EvaluationFunction;

public class PieEvaluationFunction implements EvaluationFunction<PieClassifier, Double, Boolean, Pie> {

	@Override
	public Double evaluate(PieClassifier currentState, List<Pie> trainingSet) {

		for (Pie pie : trainingSet) {

			// currentState.getDescriptor().getAnd().stream().forEach(action);

			// pie.getCrustShade()

		}

		return null;
	}

}
