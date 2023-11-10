import React from 'react';
import {BrowserRouter as Router} from 'react-router-dom';
import {Routes, Route} from 'react-router';
import './assets/scss/App.scss';

import {Main} from './component/main';
import {Guestbook} from './component/guestbook';
import {Gallery} from './component/gallery';
import SignIn from './component/user/SignIn';
import SignUp from './component/user/SignUp';
import Settings from './component/user/Settings';
import Error from './component/error/Error';
import Error404 from './component/error/Error404';
import { MySiteLayout } from './layout';

export default function App() {
    return (
        <Router>
            <Routes>
                <Route path='/' element={<MySiteLayout />}>
                    <Route index path='' element={<Main />}/>
                    <Route path='gallery' element={<Gallery />}/>
                    <Route path='guestbook' element={<Guestbook />}/>
                    <Route path='user/join' element={<SignUp />}/>
                    <Route path='user/login' element={<SignIn />}/>
                    <Route path='user/settings' element={<Settings />}/>
                    <Route path='error' element={<Error />}/> 
                    <Route path='*' element={<Error404 />}/>
                </Route>
            </Routes>
        </Router>
    );
}