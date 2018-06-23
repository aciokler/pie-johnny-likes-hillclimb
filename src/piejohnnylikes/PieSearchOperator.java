package piejohnnylikes;

import java.util.ArrayList;
import java.util.List;

import algorithm.hillclimbsearch.SearchOperator;
import piejohnnylikes.CompoundPieDescriptor.CompoundOperators;
import piejohnnylikes.Pie.PieAttribute;

public class PieSearchOperator implements SearchOperator {

	private CompoundOperators operator;
	private PieDescriptorExpression expression;

	public PieSearchOperator(PieDescriptorExpression expression, CompoundOperators operator) {
		this.expression = expression;
		this.operator = operator;
	}

	public static List<PieSearchOperator> getCombinationOfOperators() {
		List<PieSearchOperator> operators = new ArrayList<>();
		addSingularExpressions(operators);
		// addCompoundExpressions(operators);
		return operators;
	}

	private static void addSingularExpressions(List<PieSearchOperator> operators) {
		for (CompoundOperators operator : CompoundOperators.values()) {
			for (Pie.CrustShades attr : Pie.CrustShades.values()) {
				addSingleCombinationExpressions(attr, operator, operators);
			}
			for (Pie.FillingShades attr : Pie.FillingShades.values()) {
				addSingleCombinationExpressions(attr, operator, operators);
			}
			for (Pie.FillingSizes attr : Pie.FillingSizes.values()) {
				addSingleCombinationExpressions(attr, operator, operators);
			}
			for (Pie.CrustSizes attr : Pie.CrustSizes.values()) {
				addSingleCombinationExpressions(attr, operator, operators);
			}
			for (Pie.Shapes attr : Pie.Shapes.values()) {
				addSingleCombinationExpressions(attr, operator, operators);
			}
		}
	}

	private static void addSingleCombinationExpressions(PieAttribute attr, CompoundOperators operator,
			List<PieSearchOperator> operators) {
		operators.add(new PieSearchOperator(new PieDescriptor(attr, false), operator));
		operators.add(new PieSearchOperator(new PieDescriptor(attr, true), operator));
	}

	// private static void addCompoundCombinationExpressions(PieAttribute attr,
	// CompoundOperators operator,
	// List<PieSearchOperator> operators) {
	// operators.add(new PieSearchOperator(new PieDescriptor(attr, false),
	// operator));
	// operators.add(new PieSearchOperator(new PieDescriptor(attr, false),
	// operator));
	// operators.add(new PieSearchOperator(CompoundPieDescriptor.andWith(new
	// PieDescriptor(attr, true)), operator));
	// operators.add(new PieSearchOperator(CompoundPieDescriptor.orWith(new
	// PieDescriptor(attr, true)), operator));
	// }

	// private static void addCompoundExpressions(List<PieSearchOperator>
	// operators) {
	//
	// for (CompoundOperators operator : CompoundOperators.values()) {
	// for (Pie.CrustShades attr : Pie.CrustShades.values()) {
	// addCompoundCombinationExpressions(attr, operator, operators);
	// }
	// for (Pie.FillingShades attr : Pie.FillingShades.values()) {
	// addCompoundCombinationExpressions(attr, operator, operators);
	// }
	// for (Pie.FillingSizes attr : Pie.FillingSizes.values()) {
	// addCompoundCombinationExpressions(attr, operator, operators);
	// }
	// for (Pie.CrustSizes attr : Pie.CrustSizes.values()) {
	// addCompoundCombinationExpressions(attr, operator, operators);
	// }
	// for (Pie.Shapes attr : Pie.Shapes.values()) {
	// addCompoundCombinationExpressions(attr, operator, operators);
	// }
	// }
	// }

	public PieDescriptorExpression getExpression() {
		return expression;
	}

	public void setExpression(PieDescriptorExpression expression) {
		this.expression = expression;
	}

	public CompoundOperators getOperator() {
		return operator;
	}

	public void setOperator(CompoundOperators operator) {
		this.operator = operator;
	}
}
