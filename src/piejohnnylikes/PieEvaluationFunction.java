package piejohnnylikes;

import java.util.List;

import algorithm.hillclimbsearch.EvaluationFunction;
import piejohnnylikes.Pie.PieAttribute;

public class PieEvaluationFunction implements EvaluationFunction<PieClassifier, Double, Boolean, Pie> {

	@Override
	public Double evaluate(PieClassifier currentState, List<Pie> trainingSet) {

		int successfullyClassifiedCount = 0;
		for (Pie pie : trainingSet) {

			boolean value = currentState.getDescriptor().getAnd().stream()
					.allMatch(p -> apply(p.getPieAttribute(), pie));
			value = value
					|| currentState.getDescriptor().getOr().stream().anyMatch(p -> apply(p.getPieAttribute(), pie));

			if (value) {
				successfullyClassifiedCount++;
			}
		}

		return 1.00 - (double) successfullyClassifiedCount / (double) trainingSet.size();
	}

	private boolean apply(PieAttribute attribute, Pie pie) {

		if (attribute instanceof Pie.Shapes) {
			return pie.getShape() == attribute;
		} else if (attribute instanceof Pie.CrustSizes) {
			return pie.getCrustSize() == attribute;
		} else if (attribute instanceof Pie.CrustShades) {
			return pie.getCrustShade() == attribute;
		} else if (attribute instanceof Pie.FillingSizes) {
			return pie.getFillingSize() == attribute;
		} else if (attribute instanceof Pie.FillingSizes) {
			return pie.getFillingSize() == attribute;
		}

		return false;
	}

}
