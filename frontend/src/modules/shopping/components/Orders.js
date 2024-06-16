import {FormattedMessage, FormattedDate, FormattedTime} from 'react-intl';
import PropTypes from 'prop-types';

const Orders = ({orders}) => {

    return (
        <table className="table table-striped table-hover">

            <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.purchaseOrder'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.date'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.movieTitle'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.NumberOfTickets'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.totalPrice'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.sessionDate'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.Delivered'/>
                </th>

            </tr>
            </thead>

            <tbody>
            {orders.map(order =>
                <tr key={order.id}>
                    <td>
                        <div>{order.id}</div>
                    </td>
                    <td>
                        <FormattedDate value={new Date(order.date)}/> - <FormattedTime value={new Date(order.date)}/>
                    </td>
                    <td>
                        <div>{order.movieTitle}</div>
                    </td>
                    <td>
                        <div>{order.numberOfTickets}</div>
                    </td>
                    <td>
                        <div>{order.totalPrice}€</div>
                    </td>
                    <td>
                        <FormattedDate value={new Date(order.sessionDate)}/> - <FormattedTime value={new Date(order.sessionDate)}/>
                    </td>

                    {
                        order.delivered ?
                            (
                                <td>
                                    <div><FormattedMessage id='project.global.fields.isDelivered'/></div>
                                </td>
                            ) :
                            <td>
                                <div><FormattedMessage id='project.global.fields.isNotDelivered'/></div>
                            </td>
                    }

                </tr>
            )}
            </tbody>

        </table>
    );

};

Orders.propTypes = {
    orders: PropTypes.array.isRequired
};

export default Orders;

