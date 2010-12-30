package sha1;
import java.util.Arrays;


public class Sha1 {

	public static void main(String[] args) {
		Word[] h = initial();

		processChunk(h, repeat(16, Word.ZERO));
		processChunk(h, new Word[] {
				Word.ZERO, Word.ZERO, // 64-bits of crap
				Word.LEFT_BIT, // 1-bit
				Word.ZERO, Word.ZERO, Word.ZERO, Word.ZERO, Word.ZERO, Word.ZERO, Word.ZERO, Word.ZERO, Word.ZERO, Word.ZERO, Word.ZERO,
				Word.ZERO, Word.SIZE // size
				});
		final String asString = Arrays.toString(h);
		System.out.println((
				"[0d0e4793 (00001101 00001110 01000111 10010011)," +
				" 8f6e0016 (10001111 01101110 00000000 00010110)," +
				" 6e735273 (01101110 01110011 01010010 01110011)," +
				" 2ddfb7c6 (00101101 11011111 10110111 11000110)," +
				" 10f44db2 (00010000 11110100 01001101 10110010)]"
				).equals(asString));
		System.out.println(asString);
	}

	private static Word[] repeat(int time, Word what) {
		Word[] arr = new Word[time];
		for (int j = 0; j < time; ++j)
			arr[j] = what;
		return arr;
	}

	/** @param in <= 15 filled, .length == 80. */
	private static void processChunk(Word[] out, Word[] in) {
		in = extend(in);

		Word a = out[0];
		Word b = out[1];
		Word c = out[2];
		Word d = out[3];
		Word e = out[4];

		for (int i = 0; i < 80; ++i) {
			Word f;
		    if (i <= 19) {
//		        f = (b.and(c)).or((b.not()).and(d)).plus(MAGIC1);
		    	f = d.xor(b.and(c.xor(d))).plus(Word.MAGIC1);
			} else if (i <= 39) {
		        f = b.xor(c).xor(d).plus(Word.MAGIC2);
			} else if (i <= 59) {
//		        f = (b.and(c)). or(b.and(d)). or(c.and(d)).plus(MAGIC3);
		        f = (b.and(c)).xor(b.and(d)).xor(c.and(d)).plus(Word.MAGIC3);
		    } else {
		        f = b.xor(c).xor(d).plus(Word.MAGIC4);
		    }

		    final Word temp = (a.leftrotate(5)).plus(f).plus(e).plus(in[i]);
		    e = d;
		    d = c;
		    c = b.leftrotate(30);
		    b = a;
		    a = temp;
		}

		out[0] = out[0].plus(a);
		out[1] = out[1].plus(b);
		out[2] = out[2].plus(c);
		out[3] = out[3].plus(d);
		out[4] = out[4].plus(e);
	}

	private static Word[] extend(final Word[] in) {
		Word[] ret = Arrays.copyOf(in, 80);
		for (int i = 16; i < 80; ++i)
		    ret[i] = ret[i-3].xor(ret[i-8]).xor(ret[i-14]).xor(ret[i-16]).leftrotate(1);
		return ret;
	}

	static Word[] initial() {
		return new Word[] {
			Word.INIT1,
			Word.INIT2,
			Word.INIT3,
			Word.INIT4,
			Word.INIT5,
		};
	}
}
