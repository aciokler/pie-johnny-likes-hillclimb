package piejohnnylikes;

import algorithm.hillclimbsearch.SearchOperatorApplier;

public class PieSearchOperatorApplier implements SearchOperatorApplier<PieClassifier, PieSearchOperator> {

	@Override
	public PieClassifier applySearchOperator(PieClassifier inState, PieSearchOperator searchOperator) {

		PieClassifier newClassifier = new PieClassifier(inState);

		switch (searchOperator.getOperator()) {
		case AND:
			// newClassifier = new PieClassifier();
			break;
		case OR:
			break;
		}

		return newClassifier;
	}

}
