package sha1;

abstract class Bit {

	private static final Bit TRUE = new Bit() {
		@Override
		boolean booleanValue() { return true; }
	};
	private static final Bit FALSE = new Bit() {
		@Override
		boolean booleanValue() { return false; }
	};

	static Bit constant(boolean b) {
		return b ? Bit.TRUE : Bit.FALSE;
	}

	Bit xor(Bit other) {
		return Bit.constant(booleanValue() ^ other.booleanValue());
	}

	Bit and(Bit other) {
		return Bit.constant(booleanValue() & other.booleanValue());
	}

	abstract boolean booleanValue();

	@Override
	public String toString() {
		return Boolean.toString(booleanValue());
	}
}