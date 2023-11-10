import React from 'react';
import Header from "./Header";
import { Outlet } from 'react-router';
import Navigation from "./Navigation";
import Footer from "./Footer";
import styles from '../assets/scss/layout/Content.scss';

export default function MySiteLayout() {
    return (
        <>
            <Header/>
            <div className={styles.Content}>
                <Outlet />
            </div>
            <Navigation/>
            <Footer/>
        </>
    );
}