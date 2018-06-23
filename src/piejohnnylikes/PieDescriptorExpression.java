package piejohnnylikes;

public interface PieDescriptorExpression {

	public PieDescriptorExpression getCopy();

	public boolean eval(Pie pie);

	public boolean isNegateExpression();
}
