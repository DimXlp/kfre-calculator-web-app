import { Link } from 'react-router-dom';

export default function Header() {
    return (
        <header className="header">
            <div className="container">
                <div style={{display:'flex',alignItems:'center',gap:10}}>
                    <div style={{width:18,height:18, color:'var(--color-primary)'}}>
                        {/* μικρό σύμβολο */}
                        <svg viewBox="0 0 48 48" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path d="M36.7 44c-2.7 0-5.1-4.16-6.4-10.31C29 39.84 26.6 44 23.9 44s-5.1-4.16-6.4-10.31C16.3 39.84 13.9 44 11.2 44 7.2 44 4 35.05 4 24S7.2 4 11.2 4c2.8 0 5.2 4.16 6.3 10.31C18.8 8.16 21.2 4 24 4s5.1 4.16 6.4 10.31C31.6 8.16 34 4 36.7 4 40.7 4 44 12.95 44 24s-3.3 20-7.3 20Z"/>
                        </svg>
                    </div>
                    <strong style={{letterSpacing:.2}}>KFRE</strong>
                </div>

                <nav style={{display:'flex', gap:10}}>
                    <Link to="/create-account" className="btn btn-primary">Create account</Link>
                    <Link to="/login" className="btn btn-secondary">Sign in</Link>
                </nav>
            </div>
        </header>
    );
}
