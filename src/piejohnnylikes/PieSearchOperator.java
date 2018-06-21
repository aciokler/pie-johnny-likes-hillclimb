package piejohnnylikes;

import java.util.ArrayList;
import java.util.List;

import algorithm.hillclimbsearch.SearchOperator;

public class PieSearchOperator implements SearchOperator {

	private AttributeOperators operation;

	private PieDescriptor attribute;

	public PieSearchOperator(PieDescriptor attribute, AttributeOperators oper) {
		this.attribute = attribute;
		this.operation = oper;
	}

	public static List<PieSearchOperator> getCombinationOfOperators() {
		List<PieSearchOperator> operators = new ArrayList<>();
		for (AttributeOperators oper : AttributeOperators.values()) {
			for (Pie.CrustShades attr : Pie.CrustShades.values()) {
				operators.add(new PieSearchOperator(new PieDescriptor(attr, false), oper));
				operators.add(new PieSearchOperator(new PieDescriptor(attr, true), oper));
			}
			for (Pie.FillingShades attr : Pie.FillingShades.values()) {
				operators.add(new PieSearchOperator(new PieDescriptor(attr, false), oper));
				operators.add(new PieSearchOperator(new PieDescriptor(attr, true), oper));
			}
			for (Pie.FillingSizes attr : Pie.FillingSizes.values()) {
				operators.add(new PieSearchOperator(new PieDescriptor(attr, false), oper));
				operators.add(new PieSearchOperator(new PieDescriptor(attr, true), oper));
			}
			for (Pie.CrustSizes attr : Pie.CrustSizes.values()) {
				operators.add(new PieSearchOperator(new PieDescriptor(attr, false), oper));
				operators.add(new PieSearchOperator(new PieDescriptor(attr, true), oper));
			}
			for (Pie.Shapes attr : Pie.Shapes.values()) {
				operators.add(new PieSearchOperator(new PieDescriptor(attr, false), oper));
				operators.add(new PieSearchOperator(new PieDescriptor(attr, true), oper));
			}
		}
		return operators;
	}

	public AttributeOperators getOperation() {
		return operation;
	}

	public void setOperation(AttributeOperators operation) {
		this.operation = operation;
	}

	public PieDescriptor getAttribute() {
		return attribute;
	}

	public void setAttribute(PieDescriptor attribute) {
		this.attribute = attribute;
	}

	public enum AttributeOperators {
		AND, OR;
	}
}
