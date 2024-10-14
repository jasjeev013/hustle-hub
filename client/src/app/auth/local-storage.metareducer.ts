import { ActionReducer, INIT, UPDATE } from '@ngrx/store';
import { AuthState } from './auth.reducer';

export const localStorageSyncReducer = (
  reducer: ActionReducer<any>
): ActionReducer<any> => {
  return (state, action) => {
    if (action.type === INIT || action.type === UPDATE) {
      const storedState = localStorage.getItem('auth');
      if (storedState) {
        try {
          return {
            ...state,
            auth: JSON.parse(storedState),
          };
        } catch {
          // If the state cannot be parsed, continue with initial state
          return state;
        }
      }
    }

    const nextState = reducer(state, action);
    localStorage.setItem('auth', JSON.stringify(nextState.auth));
    return nextState;
  };
};
