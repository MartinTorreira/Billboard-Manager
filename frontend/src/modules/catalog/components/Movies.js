import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';
import {MovieLink} from "../../common";
import {SessionLink} from "../../common";


const Movies = ({movies}) => {

    const dateFormatter = dateString => {
        return new Date(dateString).toLocaleTimeString().slice(0, -3);
    }

    return (
    <table className="table table-striped table-hover">

        <thead>
        <tr>
            <th scope="col">
                <FormattedMessage id='project.global.fields.movie'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.session'/>
            </th>
        </tr>
        </thead>

        <tbody>
        {movies.map(movie =>
            <tr key={movie.movieId}>
                <td><MovieLink name={movie.movieTitle} id={movie.movieId}></MovieLink> </td>
                <td>{movie.sessionList.map(session =>
                    <SessionLink dateTime={dateFormatter(session.dateTime)} id={session.id}/>)}
                </td>
            </tr>
        )}
        </tbody>
    </table>
    );
};

Movies.propTypes = {
    movies: PropTypes.array.isRequired,
};

export default Movies;

