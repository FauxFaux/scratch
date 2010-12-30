package sha1;

class Word {
	private static final int BITS = 32;

	static final Word ZERO = new Word(0);
	static final Word LEFT_BIT = new Word(0x80000000);

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

	private final int val;
	private Word(int i) {
		this.val = i;
	}

	Word xor(Word other) {
		return new Word(val ^ other.val);
	}

	Word plus(Word other) {
		return new Word(val + other.val);
	}

	Word leftrotate(int amount) {
		return new Word((val << amount) | (val >>> (BITS - amount)));
	}

	Word and(Word other) {
		return new Word(val & other.val);
	}

	@Override
	public String toString() {
		return String.format("%08x (%s)", val, bits(val));
	}

	private String bits(int j) {
		final StringBuilder sb = new StringBuilder(BITS + (BITS % 8));
		for (int i = 0; i < BITS; ++i) {
			if (i != 0 && i % 8 == 0)
				sb.append(" ");
			sb.append((j & (1 << (BITS-i-1))) == 0 ? "0" : "1");
		}
		return sb.toString();
	}
}