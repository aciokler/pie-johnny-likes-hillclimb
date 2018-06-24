package piejohnnylikes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CompoundPieDescriptor implements PieDescriptorExpression {

	private boolean parentheses = true;
	private boolean negateExpression = false;
	private List<PieDescriptorExpression> operands;
	private CompoundOperators operator;

	public CompoundPieDescriptor(boolean parentheses, boolean negateExpression) {
		this.parentheses = parentheses;
		this.negateExpression = negateExpression;
	}

	public CompoundPieDescriptor(CompoundPieDescriptor descriptor) {
		this(descriptor.isParentheses(), descriptor.isNegateExpression());
		this.operator = descriptor.getOperator();
		this.operands = new ArrayList<>();
		descriptor.getOperands().forEach(o -> this.operands.add(o.getCopy()));
	}

	public CompoundPieDescriptor(List<PieDescriptorExpression> operands, CompoundOperators operator) {
		this(true, false);
		this.operator = operator;
		this.operands = operands;
	}

	public CompoundPieDescriptor(PieDescriptorExpression expr, PieDescriptorExpression expr2,
			CompoundOperators operator) {
		this(true, false);
		this.operator = operator;
		this.operands = new LinkedList<>();
		this.operands.add(expr);
		this.operands.add(expr2);
	}

	@Override
	public PieDescriptorExpression getCopy() {
		return new CompoundPieDescriptor(this);
	}

	@Override
	public boolean eval(Pie pie) {
		boolean value = false;
		switch (this.operator) {
		case AND:
			value = this.getOperands().stream().allMatch(expr -> expr.eval(pie));
			break;
		case OR:
			value = this.getOperands().stream().anyMatch(expr -> expr.eval(pie));
			break;
		default:
			throw new UnsupportedOperationException("value: " + this.operator + ", has not been implemented yet!");
		}

		return this.isNegateExpression() ? !value : value;
	}

	@Override
	public boolean contains(PieDescriptorExpression expression) {
		if (this.equals(expression)) {
			return true;
		}
		return this.getOperands().contains(expression);
	}

	@Override
	public boolean containsAnyAttribute(PieDescriptorExpression expression) {
		if (contains(expression)) {
			return true;
		}

		return this.getOperands().stream().anyMatch(expr -> expr.containsAnyAttribute(expression));
	}

	public void addOperand(PieDescriptorExpression expression) {
		if (this.contains(expression)) {
			return;
		}

		// if (this.operator == CompoundOperators.OR ||
		// !this.containsAnyAttribute(expression)) {
		// this.getOperands().add(expression);
		// }
		this.getOperands().add(expression);
	}

	public void swapOperandAt(int pos, PieDescriptorExpression expression) {
		if (this.contains(expression) || pos >= this.getOperands().size()) {
			return;
		}
		this.getOperands().remove(pos);
		this.getOperands().add(pos, expression);
	}

	public boolean isParentheses() {
		return parentheses;
	}

	public void setParentheses(boolean parentheses) {
		this.parentheses = parentheses;
	}

	public boolean isNegateExpression() {
		return negateExpression;
	}

	public void setNegateExpression(boolean negateExpression) {
		this.negateExpression = negateExpression;
	}

	public CompoundOperators getOperator() {
		return operator;
	}

	public void setOperator(CompoundOperators operator) {
		this.operator = operator;
	}

	public List<PieDescriptorExpression> getOperands() {
		return operands;
	}

	public void setOperands(List<PieDescriptorExpression> operands) {
		this.operands = operands;
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
			return this.getOperator().equals(other.getOperator()) && this.getOperands().equals(other.getOperands())
					&& this.isParentheses() == other.isParentheses()
					&& this.isNegateExpression() == other.isParentheses();
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.getOperands().hashCode() + this.getOperator().hashCode()
				+ Boolean.valueOf(this.isNegateExpression()).hashCode()
				+ Boolean.valueOf(this.isParentheses()).hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		addNegationPart(sb);
		return sb.toString();
	}

	private void addCompoundExpression(StringBuilder sb) {
		final String and = " AND ";
		final String or = " OR ";
		this.getOperands().forEach(
				expr -> sb.append(expr.toString()).append(this.getOperator() == CompoundOperators.AND ? and : or));
		if (this.getOperator() == CompoundOperators.AND) {
			sb.setLength(sb.length() - and.length());
		} else {
			sb.setLength(sb.length() - or.length());
		}
	}

	private void addNegationPart(StringBuilder sb) {
		if (this.isNegateExpression()) {
			sb.append(" NOT(");
			addCompoundExpression(sb);
			sb.append(") ");
		} else if (this.isParentheses() && this.getOperator() == CompoundOperators.AND) {
			sb.append(" [");
			addCompoundExpression(sb);
			sb.append("] ");
		} else {
			sb.append(" ");
			addCompoundExpression(sb);
			sb.append(" ");
		}
	}

	//// TODO: implement simplification method using Quine-McCluskey Algorithm
	//// or similar
	//
	// public void simplify() {
	//
	// if (this.getOperator() == CompoundOperators.OR) {
	// for (PieDescriptorExpression expr : this.getOperands()) {
	// if (expr instanceof PieDescriptor) {
	// PieDescriptor expression = (PieDescriptor) expr;
	//
	// }
	// }
	// }
	// }
}
