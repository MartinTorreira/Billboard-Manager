import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';

const SessionLink = ({id, dateTime}) => {

    return (
        <Link to={`/catalog/session-details/${id}`}>
            {dateTime + " "}
        </Link>
    );
}

SessionLink.propTypes = {
    id: PropTypes.number.isRequired,
    dateTime: PropTypes.string.isRequired
};

export default SessionLink;
