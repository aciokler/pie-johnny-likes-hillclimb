package piejohnnylikes;

import algorithm.hillclimbsearch.SearchOperatorApplier;

public class PieSearchOperatorApplier implements SearchOperatorApplier<PieClassifier, PieSearchOperator> {

	@Override
	public PieClassifier applySearchOperator(PieClassifier inState, PieSearchOperator searchOperator) {

		PieClassifier newClassifier = new PieClassifier(inState);
		switch (searchOperator.getOperation()) {
		case AND:
			newClassifier.getDescriptor().getAnd().add(searchOperator.getAttribute());
			break;
		case OR:
			newClassifier.getDescriptor().getOr().add(searchOperator.getAttribute());
			break;
		}

		return newClassifier;
	}

}
