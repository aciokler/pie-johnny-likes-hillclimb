package piejohnnylikes;

import java.util.LinkedList;
import java.util.List;

import piejohnnylikes.Pie.PieAttribute;

public class PieDescriptor {

	private List<PieDescriptor> and = new LinkedList<>();
	private List<PieDescriptor> or = new LinkedList<>();
	private boolean negateExpression = false;
	private boolean isParentheses = false;

	private PieAttribute pieAttribute;

	public PieDescriptor(PieAttribute pieAttribute) {
		this.pieAttribute = pieAttribute;
		this.negateExpression = false;
	}

	public PieDescriptor(PieAttribute pieAttribute, boolean negateExpresion) {
		this(pieAttribute);
		this.negateExpression = negateExpresion;
	}

	public PieDescriptor(PieDescriptor descriptor) {
		this.getAnd().addAll(descriptor.getAnd());
		this.getOr().addAll(descriptor.getOr());
		this.setNegateExpression(descriptor.isNegateExpression());
		this.setParentheses(descriptor.isParentheses());
		this.setPieAttribute(descriptor.getPieAttribute());
	}

	public boolean evalExpression() {
		return false;
	}

	public List<PieDescriptor> getAnd() {
		return and;
	}

	public void setAnd(List<PieDescriptor> and) {
		this.and = and;
	}

	public List<PieDescriptor> getOr() {
		return or;
	}

	public void setOr(List<PieDescriptor> or) {
		this.or = or;
	}

	public boolean isNegateExpression() {
		return negateExpression;
	}

	public void setNegateExpression(boolean negateExpression) {
		this.negateExpression = negateExpression;
	}

	public boolean isParentheses() {
		return isParentheses;
	}

	public void setParentheses(boolean isParentheses) {
		this.isParentheses = isParentheses;
	}

	public PieAttribute getPieAttribute() {
		return pieAttribute;
	}

	public void setPieAttribute(PieAttribute pieAttribute) {
		this.pieAttribute = pieAttribute;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (obj instanceof PieDescriptor) {
			PieDescriptor other = (PieDescriptor) obj;
			return this.isNegateExpression() == other.isNegateExpression()
					&& this.isParentheses() == other.isParentheses()
					&& this.getPieAttribute().equals(other.getPieAttribute()) && this.getAnd().equals(other.getAnd())
					&& this.getOr().equals(other.getOr());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.getPieAttribute().hashCode() + this.getAnd().hashCode() + this.getOr().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.isParentheses) {
			sb.append("[");
			sb.append(this.getPieAttribute());
			sb.append("]");
		}
		return sb.toString();
	}
}
