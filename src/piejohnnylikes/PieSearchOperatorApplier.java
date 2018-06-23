package piejohnnylikes;

import algorithm.hillclimbsearch.SearchOperatorApplier;

public class PieSearchOperatorApplier implements SearchOperatorApplier<PieClassifier, PieSearchOperator> {

	@Override
	public PieClassifier applySearchOperator(PieClassifier inState, PieSearchOperator searchOperator) {
		return new PieClassifier(new CompoundPieDescriptor(inState.getDescriptor().getCopy(),
				searchOperator.getExpression(), searchOperator.getOperator()));
	}

}
