import {useSelector} from 'react-redux';
import {Route, Routes} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout} from '../../users';
import {Billboard} from '../../catalog';
import users from '../../users';
import MovieDetails from "../../catalog/components/MovieDetails";
import SessionDetails from "../../catalog/components/SessionDetails";
import {PurchaseCompleted} from '../../shopping';
import {FindOrders} from '../../shopping';
import {FindOrdersResult} from '../../shopping';
import {Orders} from '../../shopping';
import DeliverForm from "../../shopping/components/DeliverForm";

const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    
   return (

        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Routes>
                <Route path="/*" element={<Home/>}/>
                <Route path="/catalog/billboard-date" element={<Billboard/>}/>
                <Route path="/catalog/movie-details/:id" element={<MovieDetails/>}/>
                <Route path="/catalog/session-details/:id" element={<SessionDetails/>}/>
                {loggedIn && <Route path="/buying/find-orders" element={<FindOrders/>}/>}
                {loggedIn && <Route path="/buying/deliver-form" element={<DeliverForm/>}/>}
                {loggedIn && <Route path="/buying/find-orders-result" element={<FindOrdersResult/>}/>}
                {loggedIn && <Route path="/users/update-profile" element={<UpdateProfile/>}/>}
                {loggedIn && <Route path="/users/change-password" element={<ChangePassword/>}/>}
                {loggedIn && <Route path="/users/logout" element={<Logout/>}/>}
                {!loggedIn && <Route path="/users/login" element={<Login/>}/>}
                {!loggedIn && <Route path="/users/signup" element={<SignUp/>}/>}
                {loggedIn && <Route path="/shopping/purchase-completed" element={<PurchaseCompleted/>}/>}
            </Routes>
        </div>

    );

};

export default Body;
