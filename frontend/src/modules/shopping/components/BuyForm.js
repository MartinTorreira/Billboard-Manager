import {useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import PropTypes from "prop-types";
import {useNavigate} from "react-router-dom";

import {Errors} from '../../common';
import * as actions from '../actions';


const BuyForm = ({sessionId}) => {

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [quantity, setQuantity] = useState(1);
    const [creditCard, setCreditCard] = useState("");
    const [backendErrors, setBackendErrors] = useState(null);
    let form;


    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.buy(sessionId, quantity, creditCard,
                () => navigate('/shopping/purchase-completed'),
                errors => setBackendErrors(errors)));

        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }
    }

    return (
        <div>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            <div className="card bg-light border-dark">
                <h5 className="card-header text-center">
                    <FormattedMessage id="project.shopping.BuyForm.title"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                          className="needs-validation" noValidate
                          onSubmit={e => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="quantity" className="col-md-2 col-form-label">
                                <FormattedMessage id="project.global.fields.quantity"/>
                            </label>
                            <div className="col-md-1">
                                <input type="number" id="quantity" className="form-control"
                                       value={quantity}
                                       onChange={e => setQuantity(Number(e.target.value))} autoFocus
                                       min="1"/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.incorrectQuantity'/>
                                </div>
                            </div>
                        </div>

                        <div className="form-group row">
                            <label htmlFor="creditCard" className="col-md-2 col-form-label">
                                <FormattedMessage id="project.global.fields.creditCard"/>
                            </label>
                            <div className="col-md-3">
                                <input type="text" id="creditCard" className="form-control"
                                       value={creditCard}
                                       onChange={e => setCreditCard(String(e.target.value))}/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.incorrectCreditCard'/>
                                </div>
                            </div>
                        </div>

                        <div className="form-group row">
                            <div className="col-sm-1 ">
                                <button type="submit" className="btn btn-primary ">
                                    <FormattedMessage id="project.shopping.buy"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );

}


BuyForm.propTypes = {
    sessionId: PropTypes.number.isRequired
};

export default BuyForm;