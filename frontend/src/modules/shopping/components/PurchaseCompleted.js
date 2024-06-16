import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';
import OrderLink from './OrderLink';
import {BackLink} from "../../common";

const PurchaseCompleted = () => {

    const orderId = useSelector(selectors.getLastOrderId);

    if (!orderId) {
        return null;
    }
    
    return (
        <>
            <div className="alert alert-success" role="alert">
                <FormattedMessage id="project.shopping.PurchaseCompleted.purchaseOrderGenerated"/>:
                &nbsp;
                <div>Tu id de compra es {orderId}</div>
                <OrderLink id={"Volver"}/>
            </div>
        </>

    );

}

export default PurchaseCompleted;
