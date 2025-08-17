import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import App from './App';
import Home from './pages/Home';
import Login from './pages/Login';
import CreateAccount from './pages/CreateAccount';

import './styles/tokens.css';
import './styles/components.css';

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <BrowserRouter>
            <Routes>
                {/* Layout route */}
                <Route path="/" element={<App />}>
                    {/* index route -> filles <Outlet /> in App */}
                    <Route index element={<Home />} />
                    <Route path="login" element={<Login />} />
                    <Route path="create-account" element={<CreateAccount />} />
                    {/* optional: 404 */}
                    <Route path="*" element={<Home />} />
                </Route>
            </Routes>
        </BrowserRouter>
    </React.StrictMode>
);
