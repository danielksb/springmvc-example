package example.springmvc.utils;

/**
 * Result of a computation. The result can hold two values. One
 * value for a successful computation and one value for an error.
 * Use this as a return value instead of exceptions if you want
 * to make the program flow more visible or declare possible
 * errors explicitly in the type signature.
 * 
 * @author Daniel
 *
 * @param <R> result type for a successful computation
 * @param <E> result type for an error
 */
public final class Result<R, E> {

	final private boolean isSuccess;
	final private R result;
	final private E error;
	
	private Result(R result, E error, boolean isSuccess) {
		this.result = result;
		this.error = error;
		this.isSuccess = isSuccess;
	}

	public boolean isSuccess() {
		return this.isSuccess;
	}
	
	/**
	 * Create a successful result.
	 * @param result
	 * @return
	 */
	public static <R, E> Result<R, E> createSuccess(R result) {
		return new Result<R, E>(result, null, true);
	}
	
	/**
	 * Create an error.
	 * 
	 * @param error
	 * @return
	 */
	public static <R, E> Result<R, E> createError(E error) {
		return new Result<R, E>(null, error, false);
	}

	public R getResult() {
		return result;
	}

	public E getError() {
		return error;
	}
}
