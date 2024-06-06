package vn.edu.hcmuaf.fit.travie.core.usecase;

import vn.edu.hcmuaf.fit.travie.core.handler.Result;
import vn.edu.hcmuaf.fit.travie.core.handler.error.DataError;

public abstract class UseCaseWithParams<Type, Params> {

    public abstract Result<Type, DataError> execute(Params params);
}
