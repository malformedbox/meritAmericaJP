import * as ActionTypes from './ActionTypes';

export const Comments = (state = { errMess: null, comments:[]}, action) => {
    switch(action.type) {
        case ActionTypes.ADD_COMMENTS:
            return {...state, isLoading: false, errMess: null, comments: action.payload} 

        case ActionTypes.COMMENTS_FAILED:
            return {...state, isLoading: false, errMess: action.payload, comments: []}
        //if the property of the action, in this case type, matches the case, this reducer function will alter the state.
        case ActionTypes.ADD_COMMENT:
            var comment = action.payload;
            //cannot modify state directly, must make a copy
            return {...state, comments: state.comments.concat(comment)};
        default:
            return state;
    }
}