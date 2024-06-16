import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';

const MovieLink = ({id, name}) => {

    return (
        <Link to={`/catalog/movie-details/${id}`}>
            {name}
        </Link>
    );
}

MovieLink.propTypes = {
    id: PropTypes.number.isRequired,
    sessions: PropTypes.array,
};

export default MovieLink;
