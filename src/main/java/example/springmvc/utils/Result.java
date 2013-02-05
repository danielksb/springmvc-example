package example.springmvc.utils;

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
	
	public static <R, E> Result<R, E> createSuccess(R result) {
		return new Result<R, E>(result, null, true);
	}
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
