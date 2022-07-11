package piejohnnylikes;

public interface PieDescriptorExpression {

	public PieDescriptorExpression getCopy();

	public boolean eval(Pie pie);

	public boolean isNegateExpression();

	public boolean contains(PieDescriptorExpression expression);

	public boolean containsAnyAttribute(PieDescriptorExpression expression);

	public enum CompoundOperators {
		AND, OR;
	}

}
