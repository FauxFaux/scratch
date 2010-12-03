import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class SpamFiles {
	public static void main(String[] args) throws InterruptedException {
		final int num;
		if (args.length >= 1)
			num = Integer.parseInt(args[0]);
		else
			num = 10000;

		final int runs;
		if (args.length >= 2)
			runs = Integer.parseInt(args[1]);
		else
			runs = 5;

		final int threads;
		if (args.length >= 3)
			threads = Integer.parseInt(args[2]);
		else
			threads = 1;

		double totaltime = 0;
		for (int j = 0; j < runs; ++j) {
			final ExecutorService pool = Executors.newFixedThreadPool(threads);
			final long start = System.nanoTime();
			for (int t = 0; t < threads; ++t) {
				final int bottom = (num / threads) * t;
				final int top = (num / threads) * (t + 1) - 1;
				pool.submit(new Runnable() {
					@Override public void run() {
						final List<File> l = new ArrayList<File>(top - bottom);
						for (int i = bottom; i < top; ++i) {
							final File f = new File("file" + i + ".tmp");
							l.add(f);
							try {
								final FileOutputStream os = new FileOutputStream(f);
								try {
									os.write("Omg POOOOOONIESSSSSSSSS".getBytes());
								} finally {
									os.close();
								}
							} catch (IOException e) {
								e.printStackTrace(); // arrrrgh
							}
						}
						for (File f : l)
							if (!f.delete())
								throw new RuntimeException(f.getName());
					}
				});
			}
			pool.shutdown();
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
			final double thisrun = (System.nanoTime() - start) / 1e9;
			totaltime += thisrun;
			System.out.println(thisrun);
		}
		System.out.println("Average: " + totaltime / runs + " seconds");
	}
}


