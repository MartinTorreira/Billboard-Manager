import {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {useState} from 'react';
import {useParams} from 'react-router-dom';
import * as selectors from '../selectors';
import * as actions from '../actions';
import {BackLink, MovieLink} from '../../common';
import {BuyForm} from "../../shopping";
import users from "../../users";
import {FormattedMessage} from "react-intl";
import {Errors} from '../../common';

const SessionDetails = () => {

    const VIEWER = "VIEWER";
    //const TICKET_SELLER = "TICKET_SELLER";

    const [backendErrors, setBackendErrors] = useState(null);
    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const session = useSelector(selectors.getSession);
    const dispatch = useDispatch();
    const {id} = useParams();
    const userRole = useSelector(users.selectors.getRole);


    useEffect(() => {

        const sessionId = Number(id);

        if (!Number.isNaN(sessionId)) {
            dispatch(actions.findSessionById(sessionId, errors => setBackendErrors(errors)));
        }

        return () => dispatch(actions.clearSession())

    }, [id, dispatch]);

    if (!session) {
        return null;
    }

    const dateFormatter = dateString => {

        const date = new Date(dateString);

        //const hour = new Intl.DateTimeFormat('es-ES', {hour:'numeric',minute:'numeric'});
        const day = new Intl.DateTimeFormat('es-ES', {day:'numeric'});
        const month = new Intl.DateTimeFormat('es-ES', { month: 'numeric' });
        const year = new Intl.DateTimeFormat('es-ES', { year: 'numeric' });

        return day.format(date)+"/"+month.format(date)+"/"+year.format(date);

    }

    const hourFormatter = dateString => {

        const date = new Date(dateString);
        const hour = new Intl.DateTimeFormat('es-Es',{hour:'numeric',minute:'numeric'});

        return hour.format(date);
    }


    return (
        <>
            <div>
                <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
                <BackLink/>
                <div className="card text-center">
                    <div className="card-body">
                        <h5 className="card-title">{"Sesión número: " + session.id}</h5>
                        <div className="mt-2">
                            <MovieLink name={session.movieTitle} id={session.movieId}></MovieLink>
                        </div>
                        <div className="mt-2"><b><FormattedMessage id='project.global.fields.movie.duration'/></b>{session.duration} minutos</div>
                        <div className="mt-2"><b><FormattedMessage id='project.global.fields.movie.prize'/></b>{session.ticketPrice} €</div>
                        <div className="mt-2"><b><FormattedMessage id='project.global.fields.movie.date'/></b>{dateFormatter(session.dateTime)}</div>
                        <div className="mt-2"><b><FormattedMessage id='project.global.fields.movie.hour'/></b>{hourFormatter(session.dateTime)}</div>
                        <div className="mt-2"><b><FormattedMessage id='project.global.fields.movie.roomName'/></b>{session.roomName} </div>
                        <div className="mt-2"><b><FormattedMessage id='project.global.fields.movie.avaiableTickets'/></b>{session.availableTickets}</div>
                    </div>
                </div>
            </div>
            {loggedIn &&
                <div>
                    <br/>{userRole === VIEWER ?
                    <BuyForm sessionId={session.id}/>
                    : <></>}
                </div>
            }
        </>
    );

}

export default SessionDetails;