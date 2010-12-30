package sha1;

import java.util.concurrent.atomic.AtomicLong;

abstract class Bit {

	public static AtomicLong XORS = new AtomicLong();
	public static AtomicLong ANDS = new AtomicLong();

	private static final Bit TRUE = new Bit() {
		@Override
		boolean booleanValue() { return true; }
	};
	private static final Bit FALSE = new Bit() {
		@Override
		boolean booleanValue() { return false; }
	};

	static class Xor extends Bit {
		private String tostr;
		private final Bit left;
		private final Bit right;

		public Xor(Bit left, Bit right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			String tostr = null;
			if (null == tostr)
				tostr = "(" + left + " ^ " + right + ")";
			return tostr;
		}
	}

	static class And extends Bit {
		private String tostr;
		private final Bit left;
		private final Bit right;

		public And(Bit left, Bit right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			String tostr = null;
			if (null == tostr)
				tostr = "(" + left + " & " + right + ")";
			return tostr;
		}
	}

	static Bit constant(boolean b) {
		return b ? Bit.TRUE : Bit.FALSE;
	}

	Bit xor(Bit other) {
		try {
			return Bit.constant(booleanValue() ^ other.booleanValue());
		} catch (NotResolvableException e) {
			XORS.incrementAndGet();
			return new Xor(this, other);
		}
	}

	Bit and(Bit other) {
		try {
			return Bit.constant(booleanValue() & other.booleanValue());
		} catch (NotResolvableException e) {
			ANDS.incrementAndGet();
			return new And(this, other);
		}
	}

	boolean booleanValue() throws NotResolvableException {
		throw new NotResolvableException();
	}

	@Override
	public String toString() {
		try {
			return booleanValue() ? "1" : "0";
		} catch (NotResolvableException e) {
			return "_";
		}
	}

	public static Bit tracked(final String name) {
		return new Bit() {
			@Override
			public String toString() {
				return "[" + name + "]";
			}
		};
	}

	static class NotResolvableException extends Exception {

	}
}