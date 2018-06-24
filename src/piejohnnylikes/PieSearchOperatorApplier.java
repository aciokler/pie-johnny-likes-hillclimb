package piejohnnylikes;

import algorithm.hillclimbsearch.SearchOperatorApplier;
import piejohnnylikes.PieDescriptorExpression.CompoundOperators;

public class PieSearchOperatorApplier implements SearchOperatorApplier<PieClassifier, PieSearchOperator> {

	@Override
	public PieClassifier applySearchOperator(PieClassifier inState, PieSearchOperator searchOperator) {

		PieDescriptorExpression expression = generateNewDescriptor(inState, searchOperator);
		// System.out.println("expr: " + expression);
		return new PieClassifier(expression);
	}

	private PieDescriptorExpression generateNewDescriptor(PieClassifier inState, PieSearchOperator searchOperator) {
		PieDescriptorExpression expression = inState.getDescriptor().getCopy();
		if (expression instanceof CompoundPieDescriptor) {
			CompoundPieDescriptor desc = (CompoundPieDescriptor) expression;
			if (desc.getOperator() == CompoundOperators.AND) {
				// expression is a specialization
				if (!desc.containsAnyAttribute(searchOperator.getExpression())) {
					desc.addOperand(searchOperator.getExpression());
					desc.simplify();
				} else {
					// expression was a specialization, replace with
					// generalization
					// desc.setNegateExpression(!desc.isNegateExpression());
					CompoundPieDescriptor general = new CompoundPieDescriptor(desc, searchOperator.getExpression(),
							CompoundOperators.OR);
					expression = general;
				}
			} else {
				// expression is a generalization
				int counter = 0;
				boolean expressionAlreadyUsed = true;
				// System.out.println("before " + desc.getOperands().size());
				do {

					PieDescriptorExpression expr = desc.getOperands().get(counter);
					if (!expr.containsAnyAttribute(searchOperator.getExpression())) {
						if (expr instanceof CompoundPieDescriptor) {
							CompoundPieDescriptor specialization = (CompoundPieDescriptor) expr;
							specialization.addOperand(searchOperator.getExpression());
						} else if (expr instanceof PieDescriptor) {
							// replace the single expression with a compound one
							CompoundPieDescriptor specialization = new CompoundPieDescriptor(expr,
									searchOperator.getExpression(), CompoundOperators.AND);
							desc.swapOperandAt(counter, specialization);
						}
						expressionAlreadyUsed = false;
						break;
					}
				} while (++counter < desc.getOperands().size());
				// System.out.println("after");

				if (expressionAlreadyUsed) {
					// if compound already contains search operator expression,
					// then add it as a generalization
					PieDescriptor negatedExpression = (PieDescriptor) searchOperator.getExpression();
					negatedExpression.setNegateExpression(negatedExpression.isNegateExpression());
					desc.addOperand(negatedExpression);
				}

				desc.simplify();
			}
		} else if (expression instanceof PieDescriptor) {
			if (!expression.containsAnyAttribute(searchOperator.getExpression())) {
				CompoundPieDescriptor specialization = new CompoundPieDescriptor(expression,
						searchOperator.getExpression(), CompoundOperators.AND);
				expression = specialization;
			} else {
				// if compound already contains search operator
				// expression, then negate it and add it as a
				// generalization
				PieDescriptor negatedExpression = (PieDescriptor) searchOperator.getExpression();
				negatedExpression.setNegateExpression(negatedExpression.isNegateExpression());
				CompoundPieDescriptor generalization = new CompoundPieDescriptor(expression, negatedExpression,
						CompoundOperators.OR);
				expression = generalization;
			}
		}

		return expression;
	}

	private PieDescriptorExpression matchExactlyAllTrainingSet(PieClassifier inState,
			PieSearchOperator searchOperator) {
		PieDescriptorExpression expression = inState.getDescriptor().getCopy();
		if (expression instanceof CompoundPieDescriptor) {
			CompoundPieDescriptor desc = (CompoundPieDescriptor) expression;
			if (desc.getOperator() == CompoundOperators.AND) {
				// expression is a specialization
				if (!desc.containsAnyAttribute(searchOperator.getExpression())) {
					desc.addOperand(searchOperator.getExpression());
					desc.simplify();
				} else {
					// expression was a specialization, replace with
					// generalization
					CompoundPieDescriptor general = new CompoundPieDescriptor(desc, searchOperator.getExpression(),
							CompoundOperators.OR);
					expression = general;
				}
			} else {
				// expression is a generalization
				int counter = 0;
				boolean expressionAlreadyUsed = true;
				// System.out.println("before " + desc.getOperands().size());
				do {

					PieDescriptorExpression expr = desc.getOperands().get(counter);
					if (!expr.containsAnyAttribute(searchOperator.getExpression())) {
						if (expr instanceof CompoundPieDescriptor) {
							CompoundPieDescriptor specialization = (CompoundPieDescriptor) expr;
							specialization.addOperand(searchOperator.getExpression());
						} else if (expr instanceof PieDescriptor) {
							// replace the single expression with a compound one
							CompoundPieDescriptor specialization = new CompoundPieDescriptor(expr,
									searchOperator.getExpression(), CompoundOperators.AND);
							desc.swapOperandAt(counter, specialization);
						}
						expressionAlreadyUsed = false;
						break;
					}
				} while (++counter < desc.getOperands().size());
				// System.out.println("after");

				if (expressionAlreadyUsed) {
					// if compound already contains search operator expression,
					// then add it as a generalization
					PieDescriptor negatedExpression = (PieDescriptor) searchOperator.getExpression();
					negatedExpression.setNegateExpression(negatedExpression.isNegateExpression());
					desc.addOperand(negatedExpression);
				}

				desc.simplify();
			}
		} else if (expression instanceof PieDescriptor) {
			if (!expression.containsAnyAttribute(searchOperator.getExpression())) {
				CompoundPieDescriptor specialization = new CompoundPieDescriptor(expression,
						searchOperator.getExpression(), CompoundOperators.AND);
				expression = specialization;
			} else {
				// if compound already contains search operator
				// expression, then negate it and add it as a
				// generalization
				PieDescriptor negatedExpression = (PieDescriptor) searchOperator.getExpression();
				negatedExpression.setNegateExpression(!negatedExpression.isNegateExpression());
				CompoundPieDescriptor generalization = new CompoundPieDescriptor(expression, negatedExpression,
						CompoundOperators.OR);
				expression = generalization;
			}
		}

		return expression;
	}
}
