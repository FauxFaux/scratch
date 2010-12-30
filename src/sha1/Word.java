package sha1;

import sha1.Bit.NotResolvableException;


class Word {
	static class Adder {
		private Bit carry = Bit.constant(false);

		Bit add(Bit a, Bit b) {
			final Bit axorb = a.xor(b);
			final Bit carryIn = carry;
			carry = axorb.and(carryIn).xor(a.and(b));
			return axorb.xor(carryIn);
		}
	}

	private static final int BITS = 32;

	static final Word ZERO = new Word(0);
	static final Word LEFT_BIT = new Word(0x80000000);
	static final Word ONE = new Word(1);

	static final Word MAGIC1 = new Word(0x5A827999);
	static final Word MAGIC2 = new Word(0x6ED9EBA1);
	static final Word MAGIC3 = new Word(0x8F1BBCDC);
	static final Word MAGIC4 = new Word(0xCA62C1D6);

	static final Word INIT1 = new Word(0x67452301);
	static final Word INIT2 = new Word(0xEFCDAB89);
	static final Word INIT3 = new Word(0x98BADCFE);
	static final Word INIT4 = new Word(0x10325476);
	static final Word INIT5 = new Word(0xC3D2E1F0);

	static final Word SIZE = new Word(512 + 64);

	final Bit[] val = new Bit[BITS];

	private Word(int v) {
		for (int i = 0; i < BITS; ++i)
			val[i] = Bit.constant((v & (1 << (BITS-i-1))) != 0);
	}

	static Word makeTracked(String name) {
		Word ret = new Word();
		final int num = 1;
		for (int i = 0; i < num; i++) {
			ret.val[i] = Bit.tracked(name + i);
		}

		for (int i = num; i < BITS; i++) {
			ret.val[i] = Bit.constant(false);
		}
		return ret;
	}

	private Word() {
		// unhiding
	}

	Word xor(Word other) {
		Word ret = new Word();
		for (int i = 0; i < BITS; ++i)
			ret.val[i] = val[i].xor(other.val[i]);
		return ret;
	}

	Word plus(Word other) {
		Adder a = new Adder();
		Word ret = new Word();
		for (int i = BITS - 1; i >= 0; --i)
			ret.val[i] = a.add(val[i], other.val[i]);
		return ret;
	}

	Word leftrotate(int amount) {
		Word ret = new Word();
		// 12345678

		// 123xxxxx -> _____123
		System.arraycopy(val, 0, ret.val, BITS-amount, amount);
		// xxx45678 -> 45678xxx
		System.arraycopy(val, amount, ret.val, 0, BITS-amount);
		return ret;
	}

	Word and(Word other) {
		Word ret = new Word();
		for (int i = 0; i < BITS; ++i)
			ret.val[i] = val[i].and(other.val[i]);
		return ret;
	}

	@Override
	public String toString() {
		int num;
		try {
			num = num();
		} catch (NotResolvableException e) {
			num = 0;
		}
		return String.format("%08x (%s)", num, bits());
	}

	private int num() throws NotResolvableException {
		int ret = 0;
		for (int i = 0; i < 32; ++i)
			ret |= (val[BITS - i - 1].booleanValue() ? 1 : 0) << i;
		return ret;
	}

	private String bits() {
		final StringBuilder sb = new StringBuilder(BITS + (BITS % 8));
		for (int i = 0; i < BITS; ++i) {
			if (i != 0 && i % 8 == 0)
				sb.append(" ");
			sb.append(val[i].toString());
		}
		return sb.toString();
	}
}