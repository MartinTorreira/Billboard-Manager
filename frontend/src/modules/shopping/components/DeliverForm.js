import {useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';
import {useNavigate} from 'react-router-dom';

import {Errors} from '../../common';
import * as actions from '../actions';
import * as selectors from '../selectors';
import OrderLink from "./OrderLink";

const DeliverForm = () => {

    let form;
    const dispatch = useDispatch();

    const [backendErrors, setBackendErrors] = useState(null);
    const [creditCard, setCreditCard] = useState("");
    const [orderId, setOrderId] = useState("");
    const [success, setSuccess] = useState(false);

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.deliverTicket(creditCard, orderId,
                () =>
                {
                    setSuccess(true);
                },

                errors => {
                        setBackendErrors(errors);
                        setSuccess(false);
                }));

        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }


    return (

        <div>

            {
                success ?
                <div className="alert alert-success" role="alert">
                    <FormattedMessage id="project.shopping.PurchaseCompleted.purchaseOrderGenerated"/>: &nbsp;
                    <div>Entradas entregadas</div>
                </div>
                :
                <div>  <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/> </div>
            }
            <div className="card bg-light">
                <h5 className="card-header text-center">
                    <FormattedMessage id="project.shopping.DeliverForm.title"/>
                </h5>
                <form ref={node => form = node} className="needs-validation" noValidate onSubmit={e => handleSubmit(e)}>
                    <div className="form-group row justify-content-center text-center">
                        <div className="col-md-4">
                            <label htmlFor="orderId" className="col-form-label">
                                <FormattedMessage id="project.global.fields.orderId"/>
                            </label>
                            <input type="text" id="orderId" className="form-control"
                                   value={orderId} autoFocus
                                   onChange={e => setOrderId(String(e.target.value))}/>
                            <div className="invalid-feedback">
                                <FormattedMessage id='project.global.validator.incorrectQuantity'/>
                            </div>
                        </div>

                        <div className="col-md-4">
                            <label htmlFor="creditCard" className="col-form-label">
                                <FormattedMessage id="project.global.fields.creditCard"/>
                            </label>
                            <input type="text" id="creditCard" className="form-control"
                                   value={creditCard}
                                   onChange={e => setCreditCard(String(e.target.value))}/>
                            <div className="invalid-feedback">
                                <FormattedMessage id='project.global.validator.incorrectCreditCard'/>
                            </div>
                        </div>
                    </div>

                    <div className="form-group row justify-content-center text-center">
                        <div className="col-md-3">
                            <button type="submit" className="btn btn-primary">
                                <FormattedMessage id="project.shopping.DeliverTicket.deliver"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    )
}


export default DeliverForm;