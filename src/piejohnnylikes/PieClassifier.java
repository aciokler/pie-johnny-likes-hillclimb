package piejohnnylikes;

import algorithm.hillclimbsearch.ClassifierState;

public class PieClassifier implements ClassifierState {

	private PieDescriptor descriptor;
	private boolean errorRate;

	public PieClassifier(PieClassifier classifierToCopy) {
		this.descriptor = new PieDescriptor(classifierToCopy.getDescriptor());
	}

	public PieDescriptor getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(PieDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	public boolean isErrorRate() {
		return errorRate;
	}

	public void setErrorRate(boolean errorRate) {
		this.errorRate = errorRate;
	}

}
