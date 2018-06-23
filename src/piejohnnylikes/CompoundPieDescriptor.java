package piejohnnylikes;

import java.util.LinkedList;
import java.util.List;

public class CompoundPieDescriptor implements PieDescriptorExpression {

	private boolean isParentheses = false;
	private boolean isNegateExpression = false;
	private List<PieDescriptorExpression> and = new LinkedList<>();
	private List<PieDescriptorExpression> or = new LinkedList<>();
	private CompoundOperators operator;

	public CompoundPieDescriptor(boolean isParentheses, boolean isNegateExpression) {
		this.isParentheses = isParentheses;
		this.isNegateExpression = isNegateExpression;
	}

	public CompoundPieDescriptor(CompoundPieDescriptor descriptor) {
		this(false, false);
		this.getAnd().addAll(descriptor.getAnd());
		this.getOr().addAll(descriptor.getOr());
		this.setParentheses(descriptor.isParentheses());
	}

	public CompoundPieDescriptor(PieDescriptorExpression expr, CompoundOperators operator) {
		this(false, false);
	}

	public static CompoundPieDescriptor andWith(PieDescriptorExpression expr) {
		CompoundPieDescriptor descriptor = new CompoundPieDescriptor(false, false);
		descriptor.getAnd().add(expr);
		return descriptor;
	}

	public static CompoundPieDescriptor orWith(PieDescriptorExpression expr) {
		CompoundPieDescriptor descriptor = new CompoundPieDescriptor(false, false);
		descriptor.getOr().add(expr);
		return descriptor;
	}

	public static CompoundPieDescriptor andWith(PieDescriptorExpression expr, PieDescriptorExpression expr2) {
		CompoundPieDescriptor descriptor = new CompoundPieDescriptor(false, false);
		descriptor.getAnd().add(expr);
		descriptor.getAnd().add(expr2);
		return descriptor;
	}

	public static CompoundPieDescriptor orWith(PieDescriptorExpression expr, PieDescriptorExpression expr2) {
		CompoundPieDescriptor descriptor = new CompoundPieDescriptor(false, false);
		descriptor.getOr().add(expr);
		descriptor.getOr().add(expr2);
		return descriptor;
	}

	public static CompoundPieDescriptor andWithAndNegate(PieDescriptorExpression expr) {
		CompoundPieDescriptor descriptor = new CompoundPieDescriptor(true, true);
		descriptor.getAnd().add(expr);
		return descriptor;
	}

	public static CompoundPieDescriptor orWithAndNegate(PieDescriptorExpression expr) {
		CompoundPieDescriptor descriptor = new CompoundPieDescriptor(true, true);
		descriptor.getOr().add(expr);
		return descriptor;
	}

	public static CompoundPieDescriptor andWithAndParentheses(PieDescriptorExpression expr) {
		CompoundPieDescriptor descriptor = new CompoundPieDescriptor(true, false);
		descriptor.getAnd().add(expr);
		return descriptor;
	}

	public static CompoundPieDescriptor orWithAndParentheses(PieDescriptorExpression expr) {
		CompoundPieDescriptor descriptor = new CompoundPieDescriptor(true, false);
		descriptor.getOr().add(expr);
		return descriptor;
	}

	@Override
	public PieDescriptorExpression getCopy() {
		return new CompoundPieDescriptor(this);
	}

	@Override
	public boolean eval(Pie pie) {
		boolean value = getAnd().stream().allMatch(p -> p.eval(pie));
		value = value || getOr().stream().anyMatch(p -> p.eval(pie));
		return value;
	}

	public List<PieDescriptorExpression> getAnd() {
		return and;
	}

	public void setAnd(List<PieDescriptorExpression> and) {
		this.and = and;
	}

	public List<PieDescriptorExpression> getOr() {
		return or;
	}

	public void setOr(List<PieDescriptorExpression> or) {
		this.or = or;
	}

	public boolean isParentheses() {
		return isParentheses;
	}

	public void setParentheses(boolean isParentheses) {
		this.isParentheses = isParentheses;
	}

	public boolean isNegateExpression() {
		return isNegateExpression;
	}

	public void setNegateExpression(boolean isNegateExpression) {
		this.isNegateExpression = isNegateExpression;
	}

	public CompoundOperators getOperator() {
		return operator;
	}

	public void setOperator(CompoundOperators operator) {
		this.operator = operator;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (obj instanceof CompoundPieDescriptor) {
			CompoundPieDescriptor other = (CompoundPieDescriptor) obj;
			return this.getAnd().equals(other.getAnd()) && this.getOr().equals(other.getOr());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.getAnd().hashCode() + this.getOr().hashCode();
	}

	public enum CompoundOperators {
		AND, OK;
	}
}
