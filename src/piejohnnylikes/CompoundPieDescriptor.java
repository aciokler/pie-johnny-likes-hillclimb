package piejohnnylikes;

public class CompoundPieDescriptor implements PieDescriptorExpression {

	private boolean parentheses = false;
	private boolean negateExpression = false;
	private PieDescriptorExpression leftOperand;
	private PieDescriptorExpression rightOperand;
	private CompoundOperators operator;

	public CompoundPieDescriptor(boolean parentheses, boolean negateExpression) {
		this.parentheses = parentheses;
		this.negateExpression = negateExpression;
	}

	public CompoundPieDescriptor(CompoundPieDescriptor descriptor) {
		this(descriptor.isParentheses(), descriptor.isNegateExpression());
		this.operator = descriptor.getOperator();
		this.leftOperand = descriptor.getLeftOperand().getCopy();
		this.rightOperand = descriptor.getRightOperand().getCopy();
	}

	public CompoundPieDescriptor(PieDescriptorExpression left, PieDescriptorExpression right,
			CompoundOperators operator) {
		this(false, false);
		this.operator = operator;
		this.leftOperand = left;
		this.rightOperand = right;
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
			value = this.getLeftOperand().eval(pie) && this.getRightOperand().eval(pie);
			break;
		case OR:
			value = this.getLeftOperand().eval(pie) || this.getRightOperand().eval(pie);
			break;
		default:
			throw new UnsupportedOperationException("value: " + this.operator + ", has not been implemented yet!");
		}

		return this.isNegateExpression() ? !value : value;
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

	public PieDescriptorExpression getLeftOperand() {
		return leftOperand;
	}

	public void setLeftOperand(PieDescriptorExpression leftOperand) {
		this.leftOperand = leftOperand;
	}

	public PieDescriptorExpression getRightOperand() {
		return rightOperand;
	}

	public void setRightOperand(PieDescriptorExpression rightOperand) {
		this.rightOperand = rightOperand;
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
			return this.getOperator().equals(other.getOperator())
					&& this.getLeftOperand().equals(other.getLeftOperand())
					&& this.getRightOperand().equals(other.getRightOperand())
					&& this.isParentheses() == other.isParentheses()
					&& this.isNegateExpression() == other.isParentheses();
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.getLeftOperand().hashCode() + this.getRightOperand().hashCode() + this.getOperator().hashCode()
				+ Boolean.valueOf(this.isNegateExpression()).hashCode()
				+ Boolean.valueOf(this.isParentheses()).hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.isParentheses()) {
			sb.append(" [");
			addNegationPart(sb);
			sb.append("] ");
		} else {
			addNegationPart(sb);
		}
		return sb.toString();
	}

	private void addCompoundExpression(StringBuilder sb) {
		sb.append(this.getLeftOperand().toString())
				.append(this.getOperator() == CompoundOperators.AND ? " AND " : " OR ")
				.append(this.getRightOperand().toString());
	}

	private void addNegationPart(StringBuilder sb) {
		if (this.isNegateExpression()) {
			sb.append(" NOT(");
			addCompoundExpression(sb);
			sb.append(") ");
		} else {
			sb.append(" ");
			addCompoundExpression(sb);
			sb.append(" ");
		}
	}

	public enum CompoundOperators {
		AND, OR;
	}
}
