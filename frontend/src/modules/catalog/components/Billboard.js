import Movies from './Movies';
import * as selectors from '../selectors';
import {FormattedMessage} from 'react-intl';
import DateSelector from "./DateSelector";
import * as actions from '../actions';
import {useSelector, useDispatch} from 'react-redux';


const Billboard = () => {
    const movies = useSelector(selectors.getMoviesSearch);
    const dispatch = useDispatch();
    const billboardDate = useSelector(selectors.getBillboardDate);

    return (

        <div>
            <DateSelector id="billboardDate" className="custom-select my-1 mr-sm-2"
                          value={billboardDate} onChange={e => dispatch(actions.getBillboard(e.target.value))} />
            {
                !movies || movies.length === 0 ?
                    (
                        <div className="alert alert-danger" role="alert">
                            <FormattedMessage id='project.catalog.getMoviesSearch.noMoviesFound'/>
                        </div>
                    ) :
                    (<Movies movies={movies} />)
            }
        </div>
    );

}

export default Billboard;